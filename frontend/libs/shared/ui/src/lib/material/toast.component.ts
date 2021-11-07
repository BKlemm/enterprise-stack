import {Component} from "@angular/core";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  template: '',
  providers: [MatSnackBar]
})
export class ToastComponent {

  constructor(private snackBar: MatSnackBar) {}

  show(message: string) {
    this.snackBar.open(message, "Schließen", {
      horizontalPosition: "center",
      duration: 6000
    });
  }
}
