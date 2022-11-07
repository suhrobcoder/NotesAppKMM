package uz.suhrob.notesapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import uz.suhrob.notesapp.android.ui.pages.notes.NavGraphs
import uz.suhrob.notesapp.android.ui.pages.notes.appCurrentDestinationAsState
import uz.suhrob.notesapp.android.ui.pages.notes.destinations.Destination
import uz.suhrob.notesapp.android.ui.pages.notes.destinations.NotesPageDestination
import uz.suhrob.notesapp.android.ui.pages.notes.startAppDestination
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesApp() {
    val navController = rememberNavController()
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination
    val currentTab = BottomNavPage.values().find { currentDestination == it.direction }
        ?: BottomNavPage.values().first()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = currentTab.label))
                },
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            BottomNavigation(navController = navController, currentTab)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { paddingValues ->
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Composable
fun BottomNavigation(
    navController: NavController,
    currentTab: BottomNavPage,
) {
    NavigationBar {
        BottomNavPage.values().forEach { destination ->
            NavigationBarItem(
                selected = destination == currentTab,
                onClick = {
                    navController.navigate(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}

enum class BottomNavPage(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int,
) {
    Notes(NotesPageDestination, Icons.Rounded.Home, R.string.notes)
}