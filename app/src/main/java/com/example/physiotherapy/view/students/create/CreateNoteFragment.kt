package com.example.physiotherapy.view.students.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentCreateNoteBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.foundations.NullFieldChecker
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.view.students.selectedStudentDetail.notes.INoteModel
import kotlinx.android.synthetic.main.fragment_create_note.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateNoteFragment : BaseFragment(), NullFieldChecker {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCreateNoteBinding
    lateinit var model: INoteModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.saveButton.setOnClickListener {
            val bundle =
                bundleOf("newNote" to createNote())


            NavHostFragment.findNavController(this).navigate(
                R.id.action_createNoteFragment_to_SSNotesFragment,
                bundle,
            )
        }
    }
    fun saveNote(callback: (Boolean) -> Unit) {


    }

    private fun createNote(): Note? = if (!hasNullField()) {
        Note(noteEditText.editableText.toString())
    } else {
        null
    }


    companion object {
        fun newInstance(): CreateNoteFragment = newInstance()
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()
}