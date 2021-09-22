package fi.aiqbal.iqbal_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment(R.layout.fragment_title) {
    // TODO: Rename and change types of parameters
    internal var activityCallBack: FragmentTitleListener? = null
    private var param1: String? = null
    private var param2: String? = null

    interface FragmentTitleListener {
        fun onButtonClick(position: Int)
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textField = view.findViewById<TextView>(android.R.id.text1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvitems = view.findViewById<RecyclerView>(R.id.rvPresidentList);
        rvitems.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rvitems.adapter = object : RecyclerView.Adapter<ItemHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
                return ItemHolder(
                    LayoutInflater.from(parent.context).inflate(
                        android.R.layout.simple_list_item_1, parent,
                        false
                    )
                )
            }

            override fun getItemCount(): Int {
                Log.d("dbg","${GlobalModel.presidents.size}")
                return GlobalModel.presidents.size
            }

            override fun onBindViewHolder(holder: ItemHolder, position: Int) {
                holder.textField.text = GlobalModel.presidents[position].name
                holder.textField.setOnClickListener {
                    Log.d("USR", "Clicked $position")

                    val bundle = bundleOf("pos" to position)
                    parentFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<DetailFragment>(R.id.fragmentContainerView, args = bundle)
                        addToBackStack(null)
                    }



                }
            }
        }
    }


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
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TitleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TitleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}