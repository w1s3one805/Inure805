package app.simple.inure.ui.preferences.mainscreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.simple.inure.R
import app.simple.inure.decorations.ripple.DynamicRippleRelativeLayout
import app.simple.inure.decorations.switchview.SwitchView
import app.simple.inure.extension.fragments.ScopedFragment
import app.simple.inure.preferences.TerminalPreferences
import app.simple.inure.ui.preferences.subscreens.TerminalBackButtonAction
import app.simple.inure.ui.preferences.subscreens.TerminalColor
import app.simple.inure.ui.preferences.subscreens.TerminalFontSize
import app.simple.inure.util.FragmentHelper

class TerminalScreen : ScopedFragment() {

    private lateinit var fontSize: DynamicRippleRelativeLayout
    private lateinit var color: DynamicRippleRelativeLayout
    private lateinit var utf8: SwitchView
    private lateinit var backButtonAction: DynamicRippleRelativeLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.preferences_terminal, container, false)

        fontSize = view.findViewById(R.id.terminal_font_size)
        color = view.findViewById(R.id.terminal_color)
        utf8 = view.findViewById(R.id.terminal_utf_switch)
        backButtonAction = view.findViewById(R.id.terminal_back_button_behavior)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPostponedEnterTransition()

        utf8.setChecked(TerminalPreferences.getUTF8State())

        fontSize.setOnClickListener {
            clearExitTransition()
            FragmentHelper.openFragment(parentFragmentManager, TerminalFontSize.newInstance(), "font_size")
        }

        color.setOnClickListener {
            clearExitTransition()
            FragmentHelper.openFragment(parentFragmentManager, TerminalColor.newInstance(), "color")
        }

        utf8.setOnSwitchCheckedChangeListener {
            TerminalPreferences.setUTF8State(it)
        }

        backButtonAction.setOnClickListener {
            clearExitTransition()
            FragmentHelper.openFragment(parentFragmentManager, TerminalBackButtonAction.newInstance(), "back_button")
        }
    }

    companion object {
        fun newInstance(): TerminalScreen {
            val args = Bundle()
            val fragment = TerminalScreen()
            fragment.arguments = args
            return fragment
        }
    }
}