package com.adalbertofjr.minhaviagem;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.adalbertofjr.minhaviagem.ui.ViagemActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by AdalbertoF on 05/02/2016.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViagemCrudTest {
    @Rule
    public ActivityTestRule<ViagemActivity> mActivityRule =
            new ActivityTestRule<ViagemActivity>(ViagemActivity.class);

    @Test
    public void crudTest(){
        inserir();
    }

    private void inserir() {
        onView(withId(R.id.destino)).perform(typeText("Canada"));
        onView(withId(R.id.lazer)).perform(click());
        onView(withId(R.id.orcamento)).perform(typeText("5"));
        onView(withId(R.id.qtd_pessoas)).perform(typeText("2"));
        onView(withId(R.id.salvar_viagem)).perform(click());

    }
}
