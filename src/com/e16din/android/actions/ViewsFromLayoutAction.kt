package com.e16din.android.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import java.awt.datatransfer.StringSelection
import java.awt.Toolkit
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.ui.Messages


class ViewsFromLayoutAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)

        project!!.baseDir.refresh(false, true)

        val doc = FileEditorManager.getInstance(project).selectedTextEditor!!.document

        val originText = doc.text.trim()

        var result = ""

        var indexOfTag = originText.indexOf("<")

        while (indexOfTag >= 0) {
            val id = "@+id/"
            val indexOfId = originText.indexOf(id, indexOfTag)
            val nextIndexOfTag = originText.indexOf("<", indexOfTag + 1)

            if (indexOfId >= 0 && indexOfId < nextIndexOfTag) {
                val type = originText.substring(indexOfTag + 1, originText.indexOf(" ", indexOfTag)).replace("\n", "")
                val name = originText.substring(indexOfId + id.length, originText.indexOf("\"", indexOfId))

                result += "$type $name;\n"
            }

            indexOfTag = nextIndexOfTag
        }

        val selection = StringSelection(result)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard

        clipboard.setContents(selection, selection)

        Messages.showMessageDialog("Views generated to clipboard.", "AndroidActions", Messages.getInformationIcon())
    }

    override fun update(event: AnActionEvent?) {
        val file = CommonDataKeys.VIRTUAL_FILE.getData(event!!.dataContext)
        val doc = FileEditorManager.getInstance(event.project!!).selectedTextEditor!!.document

        val isXml = XmlFileType.INSTANCE == file!!.fileType
        val isRes = doc.text.contains("<resources>")

        event.presentation.isEnabled = isXml && !isRes
        event.presentation.isVisible = isXml && !isRes
    }

}
