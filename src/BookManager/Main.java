package BookManager;

import java.util.Scanner;

public class Main {
    private static Book[] arrBook = new Book[100];
    private static int bookCount = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choise;
        do {
            System.out.println("********************** MENU *************************");
            System.out.println("1. Nhập thông tin cho n sách");
            System.out.println("2. Hiển thị thông tin tất cả sách");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choise = sc.nextInt();
            sc.nextLine();

            switch (choise){
                case 1:
                    inputBooks(sc);
                    break;
                case 2:
                    displayBook();
                    break;
                case 3:
                    sortBooksByIntersest();
                    break;
                case 4:
                    deleteBook(sc);
                    break;
                case 5:
                    searchBooks(sc);
                    break;
                case 6:
                    updateBook(sc);
                    break;
                case 7:
                    System.out.println("Đã thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        }while (choise != 7);
    }

    // case 1
    private static void inputBooks(Scanner scanner) {
        System.out.print("Nhập số lượng sách: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            Book book = new Book();
            book.inputData(scanner, arrBook);
            arrBook[bookCount++] = book;
        }
    }

    // case 2
    private static void displayBook() {
        System.out.println("Danh sách sản phẩm:");
        for (int i = 0; i < bookCount; i++) {
            arrBook[i].displayData();
        }
    }

    // case 3
    private static void sortBooksByIntersest() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < bookCount - 1; i++) {
                if (arrBook[i].getInterest() > arrBook[i + 1].getInterest()) {
                    Book temp = arrBook[i];
                    arrBook[i] = arrBook[i + 1];
                    arrBook[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        System.out.println("Sách sau khi sắp xếp theo lợi nhuận tăng dần:");
        for (int i = 0; i < bookCount; i++) {
            arrBook[i].displayData();
        }
    }

    // case 4
    private static void deleteBook(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        int deleteBookId = Integer.parseInt(scanner.nextLine());
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (arrBook[i].getBookId() == deleteBookId) {
                for (int j = i; j < bookCount - 1; j++) {
                    arrBook[j] = arrBook[j + 1];
                }
                arrBook[bookCount - 1] = null;
                bookCount--;
                found = true;
                System.out.println("Đã xóa sách có mã " + deleteBookId);
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sách có mã " + deleteBookId);
        }
    }

    // case 5
    private static void searchBooks(Scanner scanner) {
        System.out.print("Nhập từ khóa tìm kiếm (tên sách hoặc mô tả): ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;
        System.out.println("Kết quả tìm kiếm:");
        for (int i = 0; i < bookCount; i++) {
            Book book = arrBook[i];
            String bookTitle = book.getBookName().toLowerCase();
            String bookDescription = book.getDescriptions().toLowerCase();
            if (bookTitle.contains(keyword) || bookDescription.contains(keyword)) {
                book.displayData();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sách nào phù hợp với từ khóa tìm kiếm.");
        }
    }

    // case 6
    private static void updateBook(Scanner scanner) {
        System.out.print("Nhập mã sách cần thay đổi thông tin: ");
        int bookIdToUpdate = Integer.parseInt(scanner.nextLine());
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (arrBook[i].getBookId() == bookIdToUpdate) {
                System.out.println("Thông tin sách cần thay đổi:");
                arrBook[i].displayData();
                Book updatedBook = new Book();
                updatedBook.setBookId(bookIdToUpdate);
                updatedBook.inputData(scanner, arrBook);
                arrBook[i] = updatedBook;
                System.out.println("Thông tin sách đã được cập nhật:");
                updatedBook.displayData();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sách có mã " + bookIdToUpdate);
        }
    }
}
