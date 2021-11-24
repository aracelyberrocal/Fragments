package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fragments.databinding.ActivityMainBinding.inflate
import com.example.fragments.databinding.FragmentUserListBinding
import com.example.fragments.model.User


class UserListFragment : Fragment() {
    private  var _binding: FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

private val adapter = UserAdapter(object: OnUserListener{
    override fun onClick(user: User) {
        val action =
            UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(user.id)
        findNavController().navigate(action)
    }
})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUser.layoutManager= GridLayoutManager(context,2 )
        binding.rvUser.adapter = adapter

        val users: List<User> = List< User>(5) {
            User("$it","Name $it","Country $it","h")

        }
        adapter.submitList(users)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}