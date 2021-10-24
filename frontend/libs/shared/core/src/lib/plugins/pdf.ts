import jsPDF from "jspdf";
import html2canvas from 'html2canvas';

export class PDF {

  private data: HTMLElement
  private filename: string
  private format: string = 'a4'

  constructor(private elementId: string, private file: string) {
    this.data = document.getElementById(elementId)
    this.filename = file
  }

  download() {
    html2canvas(this.data).then(canvas => {
      const width = 208
      const height = canvas.height * width / canvas.width
      const uri = canvas.toDataURL('image/png')
      const pdf = new jsPDF('p', 'mm', this.format)
      pdf.addImage(uri, 'PNG', 0, 0, width, height)
      return pdf.save(this.filename)
    })
  }
}
