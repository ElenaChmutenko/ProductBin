import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> bin = new ArrayList<>();
        bin.add("Молоко");
        bin.add("Хлебцы диетические");
        bin.add("Хлеб Кефирный");
        bin.add("Кефир");

        StringBuilder sb = new StringBuilder("Введите номер операции или 'end' для завершения покупки:\n");
        sb.append("1 - добавить товар\n2 - показать список покупок\n");
        sb.append("3 - удалить товар из корзины\n4 - поиск покупки\nВыберите операцию:");
        while (true) {
            int operation;
            System.out.println(sb);
            try {
                String input = scanner.nextLine();
                if ("end".equals(input)) {
                    break;
                }
                operation = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод данных! Следует вводить число\n");
                continue;
            }
            switch (operation) {
                case 1:
                    addProduct(bin);
                    break;
                case 2:
                    showListProducts(bin);
                    break;
                case 3:
                    deleteProduct(bin);
                    showListProducts(bin);
                    break;
                case 4:
                    findProduct(bin);
                    break;
                default:
                    System.out.println("Проверьте номер операции!\n");

            }
        }
    }

    // Method requests which purchase to add to the list,
    // adds and displays the total number of purchases
    public static List<String> addProduct(List<String> bin) {
        System.out.println("Какую покупку хотите добавить?");
        String input = scanner.nextLine();
        String product = toUpperLetter(input);
        bin.add(product);
        StringBuilder sbAdded = new StringBuilder("Добавлен товар: ");
        sbAdded.append(product);
        sbAdded.append("\nИтого в списке покупок: ");
        sbAdded.append(bin.size());
        sbAdded.append("\n");
        System.out.println(sbAdded);

        return bin;
    }

    // Method displays all purchases with their numbering
    public static void showListProducts(List<String> bin) {
        System.out.println("Список покупок:");
        for (int i = 0; i < bin.size(); i++) {
            System.out.println(i + 1 + ". " + bin.get(i));
        }
        System.out.println();
    }
    // Method displays all purchases and prompts to enter either the number of the purchase to be deleted
    // or the name of the purchase, then deletes it if found and displays purchases again
    public static void deleteProduct(List<String> bin) {
        while (true) {
            showListProducts(bin);
            System.out.println("Какую покупку хотите удалить? Введите номер или название");
            String input = scanner.nextLine();
            StringBuilder sbDel = new StringBuilder("Товар \"");
            try {
                int indexDel = Integer.parseInt(input) - 1;
                sbDel.append(bin.get(indexDel));
                bin.remove(indexDel);
                sbDel.append("\" удалён");
                System.out.println(sbDel);
                break;
            } catch (IndexOutOfBoundsException exc1) {
                System.out.println("Не верно введён номер покупки\n");
            } catch (NumberFormatException exc2) {
                String productDel = toUpperLetter(input);
                sbDel.append(productDel);
                if (bin.remove(productDel)) {
                    sbDel.append("\" удалён");
                    System.out.println(sbDel);
                    break;
                } else {
                    System.out.println("Товар не найден");
                }
            }
        }
    }
    // Method or searching for purchases by keywords
    public static boolean findProduct(List<String> bin) {
        System.out.println("Введите текст для поиска:");
        String query = scanner.nextLine();
        String queryLower = query.toLowerCase();
        StringBuilder sbFound = new StringBuilder("Найдено:\n");

        // remember length of SB-object to check it after iteration
        int sizeSb = sbFound.length();
        for (int i = 0; i < bin.size(); i++) {
            String itemLower = bin.get(i).toLowerCase();
            if (itemLower.contains(queryLower)) {
                sbFound.append(i + 1);
                sbFound.append(". ");
                sbFound.append(bin.get(i));
                sbFound.append("\n");
            }
        }
        // check if the query found
        if (sbFound.length() <= sizeSb) {
            StringBuilder sbNotFound = new StringBuilder("Покупка не найдена\n");
            System.out.println(sbNotFound);
            return false;
        } else {
            System.out.println(sbFound);
            return true;
        }
    }

    // Method gets first character of the string and check if the letter is capital
    // if not, replaces it with capital
    public static String toUpperLetter(String input) {
        char firstLetter = input.charAt(0);
        if (!Character.isUpperCase(firstLetter)) {
            StringBuilder sb = new StringBuilder(input);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return input;
        }
    }
}

