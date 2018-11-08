package com.iramml.pokedex.Fragments

import android.support.design.widget.BottomSheetDialogFragment
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.iramml.pokedex.Model.MovementsResponse
import com.iramml.pokedex.R


class BottomSheetMovementFragment:BottomSheetDialogFragment(){
    var mTag: String? = null
    var tvNameMov:TextView?=null
    var tvType:TextView?=null
    var tvTarget:TextView?=null
    var tvDescription:TextView?=null

    var view0:View?=null
    fun newInstance(tag: String): BottomSheetMovementFragment {
        val fragment = BottomSheetMovementFragment()
        val args = Bundle()
        args.putString("TAG", tag)
        fragment.setArguments(args)
        return fragment
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTag = arguments!!.getString("TAG")
    }
    fun getData(objectResponse:MovementsResponse){
        tvNameMov?.text="${objectResponse.name.replace('-', ' ')}"
        tvType?.text="Type: ${objectResponse.type.name}"
        tvTarget?.text="Target: ${objectResponse.target.name.replace('-', ' ')}"
        for (i in 0..(objectResponse.flavor_text_entries.size-1)){
            if(objectResponse.flavor_text_entries[i].language.name=="en") {
                tvDescription?.text = "${objectResponse.flavor_text_entries[i].flavor_text}"
                break
            }
        }
    }
    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        view0= inflater.inflate(R.layout.bottom_sheet_movement, container, false)
        tvNameMov=view0?.findViewById(R.id.tvMovement)
        tvType=view0?.findViewById(R.id.tvType)
        tvTarget=view0?.findViewById(R.id.tvTarget)
        tvDescription=view0?.findViewById(R.id.tvDescription)
        return view0
    }
}
