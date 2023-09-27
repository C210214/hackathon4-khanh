package BookManager;

import java.util.Scanner;

public class Book {
    private static int currentBookId = 1;
    private int bookId;
    private String bookName;
    private String author;
    private String descriptions;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus;

    public Book(){
        this.bookId = currentBookId++;
        this.bookStatus = true;
    }

    public Book(String bookName, String author, String descriptions, double importPrice, double exportPrice, float interest) {
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }
    private static int currentIndex = 0;
    public void inputData(Scanner scanner, Book[] arrBook){
        System.out.println("Nhập tên sách: ");
        this.bookName = scanner.nextLine();
        while (!isValidBookName(bookName) || isDuplicateBookName(bookName, arrBook)) {
            if (!isValidBookName(bookName)) {
                System.out.println("Tên sách không được để trống. Vui lòng nhập lại.");
            } else {
                System.out.println("Tên sách đã trùng lặp. Vui lòng nhập lại.");
            }

            System.out.println("Nhập tên sách: ");
            this.bookName = scanner.nextLine();
        }

        System.out.println("Nhập tên tác giả: ");
        this.author = scanner.nextLine();
        while (!isValidAuthor(author)){
            if(!isValidAuthor(author)){
                System.out.println("Tên tác giả không được để trống. Vui lòng nhập lại");
            }
            System.out.println("Nhập tên tác giả: ");
            this.author = scanner.nextLine();
        }

        System.out.println("Nhập mô tả về sách: ");
        this.descriptions = scanner.nextLine();
        while (!isValidDescriptions(descriptions)){
            System.out.println("Mô tả sách không được để trống và phải ít nhất 10 ký tự. Vui lòng nhập lại");
            System.out.println("Nhập mô tả sách: ");
            this.descriptions = scanner.nextLine();
        }

        System.out.println("Nhập giá nhập của sách: ");
        this.importPrice = Double.parseDouble(scanner.nextLine());
        while (!isValidImportPrice(importPrice)) {
            System.out.println("Giá nhập không hợp lệ. Vui lòng nhập lại.");
            System.out.println("Nhập giá nhập của sách: ");
            this.importPrice = Double.parseDouble(scanner.nextLine());
        }

        System.out.println("Nhập giá bán ra của sách: ");
        this.exportPrice = Double.parseDouble(scanner.nextLine());
        while (!isValidExportPrice(exportPrice, importPrice)) {
            System.out.println("Giá bán không hợp lệ. Vui lòng nhập lại.");
            System.out.println("Nhập giá bán của sách (giá bán lớn hơn " + (importPrice * 1.2) + "): ");
            this.exportPrice = Double.parseDouble(scanner.nextLine());
        }

        calInterest();

        currentIndex++;
    }



    private boolean isValidBookName(String bookName) {
        return bookName.length() > 0;
    }

    private boolean isDuplicateBookName(String bookName, Book[] arrBook) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrBook[i] != null && arrBook[i].getBookName().equalsIgnoreCase(bookName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidAuthor(String ạuthor) {
        return ạuthor.length() > 0;
    }

    private boolean isValidDescriptions(String descriptions) {
        return descriptions.length() >= 10;
    }

    private boolean isValidImportPrice(double importPrice) {
        return importPrice > 0;
    }

    private boolean isValidExportPrice(double exportPrice, double importPrice) {
        return exportPrice > importPrice * 1.2;
    }

    public void calInterest(){
        this.interest = (float) (this.exportPrice - this.importPrice);
    }


    public void displayData(){
        System.out.println("Thông tin sách:");
        System.out.println("Mã sách: " + this.bookId);
        System.out.println("Tên sách: " + this.bookName);
        System.out.println("Tên tác giả: " + this.author);
        System.out.println("Mô tả về sách: " + this.descriptions);
        System.out.println("Giá nhập sách: " + this.importPrice);
        System.out.println("Giá bán sách: " + this.exportPrice);
        System.out.println("Lợi nhuận: " + this.interest);
        System.out.println("Trạng thái sách: " + (this.bookStatus ? "Đang bán" : "Không bán"));
        System.out.println("--------------------------------------");

    }
}
