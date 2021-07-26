package com.example.physiotherapy.view.students.selectedStudentDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.viewpager2.widget.ViewPager2
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.model.SelectedStudentSlideModel
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.model.Tag
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.view.MainActivity
import com.google.android.material.tabs.TabLayoutMediator


class SSDetailFragment() :
    BaseFragment() {

    private lateinit var binding: FragmentSelectedStudentBinding
    private lateinit var listOfFragment: ArrayList<Fragment>
    private val TAG: String = SSDetailFragment::class.java.simpleName
    private var selectedStudent: Student? = Student()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_selected_student,
            container,
            false
        )
        val tag: Tag = Tag("High Priority", R.color.colorPrimary)

        selectedStudent = requireArguments().getSerializable("student") as Student;

        val task = Task("Get Groceries")
        val s = task.title
        // binding.selectedStudentNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //binding.selectedStudentNavView.itemIconTintList = null;

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager2SelectedStudent = binding.selectedStudentPager
        val tabLayoutSelectedStudent = binding.selectedStudentTabLayout

        val list: MutableList<SelectedStudentSlideModel> = ArrayList()
        //drawable klasörünüze 3 adet resim yüklendiğini varsayıyoruz
        list.add(SelectedStudentSlideModel(getString(R.string.g_revler), R.drawable.flag_poland))
        list.add(SelectedStudentSlideModel(getString(R.string.notlar), R.drawable.flag_poland))

        binding.selectedStudentPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL;
        binding.selectedStudentPager.adapter = SelectedStudentSlidePagerAdapter(list)
        binding.selectedStudentPager.adapter = SSFragmentSlidePagerAdapter(this, selectedStudent)


            binding.selectedStudentPager.currentItem = 1


        TabLayoutMediator(tabLayoutSelectedStudent, viewPager2SelectedStudent) { tab, position ->
            if (position == 0) {
                tab.text = getString(R.string.g_revler)
            } else if (position == 1) {
                tab.text = getString(R.string.notlar)
            }
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE, TAG)
        (this.requireActivity() as MainActivity).binding.toolbarLayout.toolbarSaveBtn.setOnClickListener {
            onAddButtonClicked()
        }
    }

    companion object {
        fun newInstance() = SSDetailFragment()
    }

    private fun onAddButtonClicked() {

        NavHostFragment.findNavController(this).navigate(
            R.id.action_selectedStudentFragment_to_createNoteFragment,
            null,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }

}