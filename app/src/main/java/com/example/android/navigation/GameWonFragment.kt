/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.fragment.app.Fragment
import com.example.android.navigation.databinding.FragmentGameWonBinding
import android.widget.Toast
import androidx.core.app.ShareCompat


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener {
            view: View -> view.findNavController().navigate(
                GameWonFragmentDirections
                    .actionGameWonFragmentToGameFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if (this.getShareIntent().resolveActivity(this.requireActivity().packageManager) ==
                null)
        {
            menu.findItem(R.id.share)?.isVisible = false
        }

    }

    private fun getShareIntent() : Intent
    {
        var args = GameWonFragmentArgs.fromBundle(this.requireArguments())
        // val shareIntent = Intent(Intent.ACTION_SEND)
        // shareIntent.setType("text/plain").putExtra(
        //     Intent.EXTRA_TEXT,
        //     getString(R.string.share_success_text,
        //               args.numCorrect,
        //               args.numQuestions)
        // )
        val shareIntent = ShareCompat.IntentBuilder.from(this.activity!!).setText(
           getString(R.string.share_success_text, args.numCorrect,
                     args.numQuestions))
            .setType("text/plain")
            .intent
        return shareIntent
    }

    private fun shareSuccess()
    {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
