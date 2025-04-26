package com.example.labs

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VPAdapter(fm: Fragment) : FragmentStateAdapter(fm)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return TaskListFragment()
    }
}