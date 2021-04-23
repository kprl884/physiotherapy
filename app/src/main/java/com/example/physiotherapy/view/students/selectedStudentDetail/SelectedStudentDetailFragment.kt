package com.example.physiotherapy.view.students.selectedStudentDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentBinding
import com.example.physiotherapy.model.SelectedStudentSlideModel
import com.example.physiotherapy.model.Tag
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.view.BaseFragment


class SelectedStudentDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentSelectedStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_selected_student,
            container,
            false)
        val tag:Tag = Tag("High Priority", R.color.colorPrimary)

        val task = Task("Get Groceries")

        val s = task.title
       // binding.selectedStudentNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //binding.selectedStudentNavView.itemIconTintList = null;
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: MutableList<SelectedStudentSlideModel> = ArrayList()
        //drawable klasörünüze 3 adet resim yüklendiğini varsayıyoruz
        //drawable klasörünüze 3 adet resim yüklendiğini varsayıyoruz
        list.add(SelectedStudentSlideModel("Image Title 1", R.drawable.flag_poland))
        list.add(SelectedStudentSlideModel("Image Title 2", R.drawable.flag_poland))
        list.add(SelectedStudentSlideModel("Image Title 3", R.drawable.flag_poland))
        binding.selectedStudentPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL;
        binding.selectedStudentPager.adapter = SelectedStudentSlidePagerAdapter(list)

        binding.selectedStudentPager.registerOnPageChangeCallback(object :
            OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("Selected_Page", position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })


        /*
        binding.selectedStudentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = SelectedStudentDetailAdapter(mutableListOf(
            Task("Testing One!"),
            Task("Testing Two"),
            Task("Testing Three")
        ))
        binding.selectedStudentRecyclerView.adapter = adapter
         */
    }

    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE)
    }

    companion object {
        fun newInstance() = SelectedStudentDetailFragment()
    }
}