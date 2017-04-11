package shengzheng.educationapp.ContentAdapterData;

/**
 * Created by john on 2016/9/21.
 */
public class NavMeFragmentContentProvider {
    private int icon;
    private String title;
    private int iconTail;

    public NavMeFragmentContentProvider(int icon, String title, int iconTail) {
        this.icon = icon;
        this.title = title;
        this.iconTail = iconTail;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconTail() {
        return iconTail;
    }

    public void setIconTail(int iconTail) {
        this.iconTail = iconTail;
    }

    @Override
    public String toString() {
        return "NavMeFragmentContentProvider{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", iconTail=" + iconTail +
                '}';
    }
}
