import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum DiskType {
    DVD,
    CD_R,
    MINI_DISC
}

enum Category {
    MOVIES,
    MUSIC,
    SOFTWARE
}

class Disk {
    private String title;
    private DiskType type;
    private Category category;
    private String description;

    public Disk(String title, DiskType type, Category category, String description) {
        this.title = title;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public DiskType getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Disk: " + title + " (" + type + ", " + category + ") - " + description;
    }
}

class DiskCollection {
    private List<Disk> disks;

    public DiskCollection() {
        this.disks = new ArrayList<>();
    }

    public void addDisk(String title, DiskType type, Category category, String description) {
        Disk disk = new Disk(title, type, category, description);
        disks.add(disk);
    }

    public void removeDisk(String title) {
        disks.removeIf(disk -> disk.getTitle().equals(title));
    }

    public void editDisk(String title, DiskType newType, Category newCategory, String newDescription) {
        for (Disk disk : disks) {
            if (disk.getTitle().equals(title)) {
                disk = new Disk(title, newType, newCategory, newDescription);
                break;
            }
        }
    }

    public void displayCollection() {
        System.out.println("Disk Collection:");
        for (Disk disk : disks) {
            System.out.println(disk);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DiskCollection diskCollection = new DiskCollection();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Disk");
            System.out.println("2. Remove Disk");
            System.out.println("3. Edit Disk");
            System.out.println("4. Display Collection");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter disk title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter disk type (DVD, CD_R, MINI_DISC):");
                    DiskType type = DiskType.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("Enter disk category (MOVIES, MUSIC, SOFTWARE):");
                    Category category = Category.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("Enter disk description:");
                    String description = scanner.nextLine();
                    diskCollection.addDisk(title, type, category, description);
                    break;
                case 2:
                    System.out.println("Enter disk title to remove:");
                    String removeTitle = scanner.nextLine();
                    diskCollection.removeDisk(removeTitle);
                    break;
                case 3:
                    System.out.println("Enter disk title to edit:");
                    String editTitle = scanner.nextLine();
                    System.out.println("Enter new disk type (DVD, CD_R, MINI_DISC):");
                    DiskType newType = DiskType.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("Enter new disk category (MOVIES, MUSIC, SOFTWARE):");
                    Category newCategory = Category.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println("Enter new disk description:");
                    String newDescription = scanner.nextLine();
                    diskCollection.editDisk(editTitle, newType, newCategory, newDescription);
                    break;
                case 4:
                    diskCollection.displayCollection();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}
