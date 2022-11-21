package uz.suhrob.notesapp.presentation.add_note.confirm_dialog

import com.arkivanov.decompose.ComponentContext

class ConfirmDialogComponent(
    componentContext: ComponentContext,
    private val onCancel: () -> Unit,
    private val onDiscard: () -> Unit,
) : ConfirmDialog, ComponentContext by componentContext {

    override fun cancel() {
        onCancel()
    }

    override fun discard() {
        onDiscard()
    }
}