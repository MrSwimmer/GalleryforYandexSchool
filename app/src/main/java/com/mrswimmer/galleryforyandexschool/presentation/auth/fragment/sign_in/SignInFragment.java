package com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_in;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInFragment extends BaseFragment implements SignInFragmentView {
    @InjectPresenter
    SignInFragmentPresenter presenter;

    @ProvidePresenter
    public SignInFragmentPresenter presenter() {
        return new SignInFragmentPresenter();
    }

    String email, password;

    @BindView(R.id.sign_in_email)
    EditText editEmail;
    @BindView(R.id.sign_in_password)
    EditText editPass;
    @BindView(R.id.sign_in_enter)
    Button signin;
    @BindView(R.id.sign_in_reg)
    Button signup;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_sign_in;
    }

    @OnClick(R.id.sign_in_enter)
    void onEnterClick() {
        //presenter.goToMain();
        email = editEmail.getText().toString();
        password = editPass.getText().toString();
        enter();
    }

    @OnClick(R.id.sign_in_reg)
    void onRegClick() {
        presenter.gotoReg();
    }

    void enter() {
        if (checkOnFillingFields()) {
            presenter.enter(email, password);
            signin.setClickable(false);
            signup.setClickable(false);
        } else {
            showToast("Запоните все поля");
        }
    }

    boolean checkOnFillingFields() {
        if (email.equals("") || password.equals(""))
            return false;
        return true;
    }

    @Override
    public void trySignInAgain() {
        signin.setClickable(true);
        signup.setClickable(true);
    }
}
