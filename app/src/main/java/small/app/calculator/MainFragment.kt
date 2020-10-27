package small.app.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import small.app.calculator.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    var model: ViewModel = ViewModel()
    lateinit var binding: FragmentMainBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentMainBinding.inflate(inflater)
        binding.model = model

        //Observer to update the display when a button is click
        model.display.observe(
            viewLifecycleOwner,
            { newDisplayStringBuilder ->
                binding.display.text = newDisplayStringBuilder.toString()
            }
        )

//        binding.button0.setOnClickListener(View.OnClickListener { model.addDigit('0') })
//        binding.button1.setOnClickListener(View.OnClickListener { model.addDigit('1') })
//        binding.button2.setOnClickListener(View.OnClickListener { model.addDigit('2') })
//        binding.button3.setOnClickListener(View.OnClickListener { model.addDigit('3') })
//        binding.button4.setOnClickListener(View.OnClickListener { model.addDigit('4') })
//        binding.button5.setOnClickListener(View.OnClickListener { model.addDigit('5') })
//        binding.button6.setOnClickListener(View.OnClickListener { model.addDigit('6') })
//        binding.button7.setOnClickListener(View.OnClickListener { model.addDigit('7') })
//        binding.button8.setOnClickListener(View.OnClickListener { model.addDigit('8') })
//        binding.button9.setOnClickListener(View.OnClickListener { model.addDigit('9') })
//        binding.buttonDot.setOnClickListener(View.OnClickListener { model.addDigit('.') })
//
//        binding.buttonPlus.setOnClickListener { model.changeOp('+') }
//        binding.buttonMinus.setOnClickListener { model.changeOp('-') }
//        binding.buttonMultiply.setOnClickListener { model.changeOp('*') }
//        binding.buttonDiv.setOnClickListener { model.changeOp('/') }
//        binding.buttonClear.setOnClickListener { model.changeOp('C') }
//        binding.buttonEqual.setOnClickListener { model.changeOp('E') }


        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}