import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ChatService } from '../chat.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent {
  chatForm: FormGroup;
  messages: { content: string, sender: 'user' | 'bot' }[] = [];

  constructor(
    private fb: FormBuilder,
    private chatService: ChatService,
    private authService: AuthService
  ) {
    this.chatForm = this.fb.group({
      message: ['']
    });
  }

  sendMessage(): void {
    const message = this.chatForm.value.message;
    if (!message) return;

    this.messages.push({ content: message, sender: 'user' });
    this.chatForm.reset();

    // Add a placeholder for the bot's response
    this.messages.push({ content: '', sender: 'bot' });
    const botMessageIndex = this.messages.length - 1;

    this.chatService.sendMessage(message).subscribe({
      next: (chunk) => {
        // Append the chunk to the bot's message content
        this.messages[botMessageIndex].content += chunk;
      },
      error: (err) => {
        console.error('Error streaming response:', err);
        this.messages[botMessageIndex].content = 'Error: Could not get response from server.';
      }
    });
  }

  logout(): void {
    this.authService.logout();
  }
}