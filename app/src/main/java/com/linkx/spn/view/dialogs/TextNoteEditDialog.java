package com.linkx.spn.view.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.linkx.spn.R;
import com.linkx.spn.data.models.Note;
import com.linkx.spn.data.models.Project;
import com.linkx.spn.data.models.Status;
import com.linkx.spn.data.models.Step;
import com.linkx.spn.data.models.TextNote;
import com.linkx.spn.data.services.LastVisitedProjectChangedEvent;
import com.linkx.spn.data.services.ProjectVisitingService;
import com.linkx.spn.data.services.RxEventBus;
import java.util.Collections;
import java.util.List;

/**
 * Created by ulyx.yang on 2016/9/17.
 */
public class TextNoteEditDialog extends DialogFragment {
    @Bind(R.id.text_note_edit_area)
    EditText textNoteEditor;
    @Bind(R.id.text_note_cancel)
    ImageView cancel;
    @Bind(R.id.text_note_save)
    ImageView save;

    private TextNote textNote = null;

    public static TextNoteEditDialog create(TextNote textNote) {
        TextNoteEditDialog stepsInputDialog = new TextNoteEditDialog();
        Bundle bundle = new Bundle();
        bundle.putString("_text_note", textNote.toJson());
        stepsInputDialog.setArguments(bundle);
        return stepsInputDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fdialog_text_note_editor, container);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();
        if (bundle.containsKey("_text_note")) {
            textNote = TextNote.fromJson(bundle.getString("_text_note"), TextNote.class);
        }

        cancel.setOnClickListener(v -> {
            this.dismiss();
        });
        save.setOnClickListener(v -> {
            String text = textNoteEditor.getText().toString();
            saveTextNote(textNote, text);
//            RxEventBus.post(new LastVisitedProjectChangedEvent(textNoteId));
            this.dismiss();
        });

        return view;
    }

    private TextNote saveTextNote(TextNote textNote, String text) {
        // TODO
        // get project id
        ProjectVisitingService pvs = ProjectVisitingService.worker();
        TextNote newTextNote;
        if (null == textNote) { // new text note
            String id = pvs.generateTextNoteId();
            newTextNote = TextNote.create(Note.create(id, System.currentTimeMillis()), text);
        } else {
            newTextNote = TextNote.create(textNote.basic(), text);
        }
        pvs.saveTextNote(newTextNote);
        return newTextNote;
    }

}
