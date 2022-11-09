package uz.suhrob.notesapp.android.ui.pages.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import uz.suhrob.notesapp.android.ui.pages.categories.CategoriesPage
import uz.suhrob.notesapp.android.ui.pages.notes.NotesPage
import uz.suhrob.notesapp.presentation.home.Home

@Suppress("OPT_IN_USAGE")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    component: Home,
) {
    val childStack by component.childStack.subscribeAsState()
    val currentTab by component.currentTab.subscribeAsState()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = currentTab.title)
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            NavigationBar {
                Home.Tab.values().forEach { tab ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title
                            )
                        },
                        label = { Text(text = tab.title) },
                        selected = currentTab == tab,
                        onClick = { component.tabClick(tab) },
                    )
                }
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { paddingValues ->
        Children(
            stack = childStack,
            modifier = Modifier.padding(paddingValues),
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is Home.Child.NotesChild -> NotesPage(component = child.component)
                is Home.Child.CategoriesChild -> CategoriesPage()
            }
        }
    }
}

private val Home.Tab.icon: ImageVector
    get() {
        return when (this) {
            Home.Tab.Notes -> Icons.Rounded.Home
            Home.Tab.Categories -> Icons.Rounded.Menu
        }
    }