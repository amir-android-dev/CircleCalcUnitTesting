package com.amir.circlecalcunittesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest {

    private lateinit var calcViewModel: CalcViewModel
    //To construct a CalcViewModel instance we need a Calculations instance. For that we need mockito.
    private lateinit var calculations: Calculations

    //before testing our test methods;
    /* Since we are going to assert LIveData,
     we need to add InstantTaskExecutorRule to this class.
     This rule runs all Architecture Components-related background jobs in the same thread
      so that the test results happen synchronously, and in a repeatable order.*/
    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @Before
    fun setUp() {
        calculations = Mockito.mock(Calculations::class.java)
        // a mock allows tester to set answers to method calls when writing the test case.
        //this how we do it
        Mockito.`when`(calculations.calculateArea(2.1)).thenReturn(13.8474)
        Mockito.`when`(calculations.calculateCircumference(2.1)).thenReturn(13.188)
        calcViewModel = CalcViewModel(calculations)
    }

    //what or which - what you do (Action) - what will be (the result)
    @Test
    fun calculateArea_radiusSent_updateLiveData() {
        calcViewModel.calculateArea(2.1)
        val result=calcViewModel.areaValue.value
        assertThat(result).isEqualTo("13.8474")
    }

    @Test
    fun calculateCircumference_radiusSent_updateLiveData() {
        calcViewModel.calculateCircumference(2.1)
        val result=calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("13.188")
    }
}
