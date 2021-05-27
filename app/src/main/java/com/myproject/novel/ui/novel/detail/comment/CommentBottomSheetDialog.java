package com.myproject.novel.ui.novel.detail.comment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.myproject.novel.R;
import com.myproject.novel.local.util.CommonUtils;


import static androidx.appcompat.content.res.AppCompatResources.getDrawable;


public class CommentBottomSheetDialog extends BottomSheetDialogFragment implements OnKeyboardVisibilityListener {

    private   View rootView;
    private EditText editText;

    @Override
    public void onCreate( Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();

        if(dialog != null) {
            dialog.setOnShowListener(dialog1 -> {
                FrameLayout bottomSheet = (FrameLayout)
                        dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                if(bottomSheet != null) {
                    BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    behavior.setDraggable(false);
                    behavior.setPeekHeight(0);
                    bottomSheet.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
                    ViewCompat.setBackground(bottomSheet, getDrawable(requireContext(),R.drawable.rounded_dialog));
                }
            });
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
       CommonUtils.enableLightStatusBar(requireActivity());
        rootView = inflater.inflate(R.layout.modal_comment_full,container, false);

        setKeyboardVisibilityListener(this);

        editText = rootView.findViewById(R.id.structured_edittext_answer);
        ImageButton backButton = rootView.findViewById(R.id.back_btn);
        backButton.setOnClickListener(v -> {
            dismiss();
        });
        return rootView;
    }



    @Override
    public void onVisibilityChanged(boolean visible) {
      if(visible) {
          editText.setLines(5);
      } else {
          editText.setLines(1);
          Log.i("Keyboard state", "Ignoring global layout change...");
      }
    }


    private void setKeyboardVisibilityListener(final OnKeyboardVisibilityListener onKeyboardVisibilityListener) {

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private boolean alreadyOpen;
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int defaultKeyboardHeightDP = 100;
                int estimatedKeyboardDP = defaultKeyboardHeightDP + 48;
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, estimatedKeyboardDP, rootView.getResources().getDisplayMetrics());
                rootView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = rootView.getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;

                if (isShown == alreadyOpen) {
                    Log.i("Keyboard state", "Ignoring global layout change...");
                    rootView.setPadding(0, 0, 0, heightDiff);
                    return;
                } else {
                    if (rootView.getPaddingBottom() != 0) {
                        //reset the padding of the contentView
                        rootView.setPadding(0, 0, 0, 0);
                    }
                    alreadyOpen = isShown;
                }

                onKeyboardVisibilityListener.onVisibilityChanged(isShown);
            }
        });
}
}
