package com.android.samples.arch.componentsbasicsample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation

class StartFragment : Fragment() {

    private lateinit var viewModel: StartViewModel

    companion object {
        fun newInstance() = StartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Obtain ViewModel from ViewModelProviders, using this fragment as LifecycleOwner.
        viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)

        // Observe data on the ViewModel, exposed as a LiveData
        viewModel.data.observe(this, Observer { data ->
            // Set the text exposed by the LiveData
            view?.findViewById<TextView>(R.id.text)?.text = data
        })

        // Set up a click listener on the login button
        view?.findViewById<Button>(R.id.navigate_bt)?.setOnClickListener {
            // Navigate to the login destination
            view?.let { Navigation.findNavController(it).navigate(R.id.end_action) }
        }
    }

}