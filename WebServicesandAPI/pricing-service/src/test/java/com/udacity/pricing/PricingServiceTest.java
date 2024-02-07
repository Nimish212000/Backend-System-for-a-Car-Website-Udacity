package com.udacity.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.udacity.pricing.PricingServiceApplication;
import org.udacity.pricing.api.PricingController;
import org.udacity.pricing.service.PricingService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
@ContextConfiguration(classes = PricingServiceApplication.class)
public class PricingServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PricingService pricingService;

    @Test
    public void testDynamicVehicleIds() throws Exception {
        int totalRequests = 1000;

        for (int i = 1; i <= totalRequests; i++) {
            int id = i;
            mockMvc.perform(get("/services/price")
                            .param("vehicleId", String.valueOf(i))
                            .contentType("application/json")
                            .accept("application/json"))
                    .andExpect(result -> {
                        System.out.println("Request for Vehicle ID " + id + " - Status: " + result.getResponse().getStatus());
                        System.out.println("Response Content: " + result.getResponse().getContentAsString());
                    })
                    .andExpect(status().is(i <= 19 ? 200 : 404));
        }
    }

}
