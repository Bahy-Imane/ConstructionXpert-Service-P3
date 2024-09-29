import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { NgIf } from '@angular/common';
import { AuthService } from "../../core/services/auth-service";
import { animate, style, transition, trigger } from "@angular/animations";
import {MatProgressSpinner} from "@angular/material/progress-spinner";

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    NgIf,
    MatProgressSpinner
  ],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('500ms ease-out', style({ opacity: 1 })),
      ]),
    ]),
  ],
})
export class SignUpComponent {
  signupForm: FormGroup;
  success = false;
  failure = false;
  errorMessage: string | null = null;
  loading = false;  // Ajout de l'état de chargement

  private authService = inject(AuthService);
  private router = inject(Router);

  constructor(private fb: FormBuilder) {
    this.signupForm = this.fb.group({
      fullName: ['', [Validators.required]],
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSignup() {
    if (this.signupForm.valid) {
      this.loading = true;  // Activation du spinner de chargement
      const userDto = this.signupForm.value;

      this.authService.SignUp(userDto).subscribe({
        next: (response) => {
          this.loading = false;  // Désactivation du spinner
          this.success = true;
          this.router.navigate(['/login']);
        },
        error: (error) => {
          this.loading = false;  // Désactivation du spinner en cas d'échec
          this.failure = true;
          this.errorMessage = 'Signup failed. Please try again.';
        }
      });
    }
  }
}
