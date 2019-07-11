package gdyj.tydic.com.jinlingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

// 1.创建Adapter
public class TestFragmentAdapter extends FragmentPagerAdapter {
    // 用来添加Fragment
    private List<Fragment> fragments;
    // 用来存储Fragment标题
    private String title[] = {"首页","成长树","发现","我的"};

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public TestFragmentAdapter(FragmentManager fm) {
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
