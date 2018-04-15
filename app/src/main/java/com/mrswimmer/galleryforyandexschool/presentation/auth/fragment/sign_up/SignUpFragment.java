package com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_up;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpFragment extends BaseFragment implements SignUpFragmentView {
    @InjectPresenter
    SignUpFragmentPresenter presenter;

    @ProvidePresenter
    public SignUpFragmentPresenter presenter() {
        return new SignUpFragmentPresenter();
    }

    @BindView(R.id.sign_up_email)
    EditText editEmail;
    @BindView(R.id.sign_up_password)
    EditText editPass;
    @BindView(R.id.sign_up_username)
    EditText editUsername;

    String email, pass, username;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sign_up;
    }

    @OnClick(R.id.sign_up_reg)
    void onRegClick() {
        email = editEmail.getText().toString();
        pass = editPass.getText().toString();
        username = editUsername.getText().toString();
        signUp();
    }

    void signUp() {
        if (checkOnFillingFields()) {
            presenter.signUp(email, pass, username);
        } else {
            showToast("Заполните все поля!");
        }
    }

    boolean checkOnFillingFields() {
        if (email.equals("") || pass.equals("") || username.equals(""))
            return false;
        return true;
    }
}
