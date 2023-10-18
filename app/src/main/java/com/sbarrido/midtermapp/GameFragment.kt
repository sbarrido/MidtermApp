package com.sbarrido.midtermapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sbarrido.midtermapp.databinding.FragmentGameBinding
import com.sbarrido.midtermapp.GameViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = HighScoreDB.getInstance(application).scoreDao
        val viewModelFactory = GameViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        binding.gameModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.okButton.setOnClickListener {
            val response = viewModel.makeGuess(binding.guessText.text.toString().toInt())
            val toast = Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT)
            toast.show()

            binding.attemptsTV.setText("Number of Attempts: " + viewModel.numGuess)
            if(viewModel.isWon()) {
                val action = GameFragmentDirections
                    .actionGameFragmentToMainFragment()
                view.findNavController().navigate(action)
            }
        }

        binding.minusButton.setOnClickListener {
            viewModel.decrement()
            binding.guessText.setText(viewModel.currGuess.toString())
        }
        binding.plusButton.setOnClickListener {
            viewModel.increment()
            binding.guessText.setText(viewModel.currGuess.toString())
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}