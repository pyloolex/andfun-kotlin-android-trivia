package com.example.android.navigation

import android.os.Bundle
//import android.support.v4.app.Fragment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.ui.NavigationUI
import androidx.navigation.findNavController
import android.view.MenuInflater
import com.example.android.navigation.databinding.FragmentTitleBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation


/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)
        binding.playButton.setOnClickListener {
            view: View ->
                view.findNavController().navigate(
                    TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return NavigationUI.onNavDestinationSelected(
            item, this.view!!.findNavController()) ||
        super.onOptionsItemSelected(item)
    }
}
