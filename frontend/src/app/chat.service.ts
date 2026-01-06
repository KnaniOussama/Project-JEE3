import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private baseUrl = '/api/chat';

  constructor(private http: HttpClient) { }

  sendMessage(message: string): Observable<string> {
    return new Observable<string>(observer => {
      const controller = new AbortController();

      fetch(`${this.baseUrl}/stream`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'text/event-stream'
        },
        body: JSON.stringify({ message }),
        signal: controller.signal
      }).then(async response => {
        if (!response.body) {
          throw new Error('Response body is null');
        }
        const reader = response.body.getReader();
        const decoder = new TextDecoder();

        while (true) {
          const { done, value } = await reader.read();
          if (done) {
            break;
          }
          const chunk = decoder.decode(value, { stream: true });
          // SSE messages are in the format "data: content\n\n"
          // We split by newline and process each line
          const lines = chunk.split('\n').filter(line => line.trim().startsWith('data:'));
          for (const line of lines) {
            const data = line.substring('data:'.length);
            if (data) {
              observer.next(data);
            }
          }
        }
        observer.complete();
      }).catch(err => {
        if (err.name !== 'AbortError') {
          observer.error(err);
        }
      });

      // When the observable is unsubscribed, abort the fetch request
      return () => {
        controller.abort();
      };
    });
  }
}