package JvSi.ShanJi.com.English.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * @author binshengzhu
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] title = {"首页", "成长树", "发现", "我的"};

    void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // 设置Fragment标题
        return title[position];
    }
}
