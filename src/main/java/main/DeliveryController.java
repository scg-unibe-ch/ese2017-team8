package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * In this class the deliveries are prepared in lists to be later on used.
 * With @link{showAllDelivery} you are able to retrieve the list of all deliveries.
 * With @link{showAllDeliveryDriver} you are able to get the deliveries for a specific driver.
 *
 * @author Team8
 * @version 1.0
 */

//@Controller
//public class DeliveryController {
//
//
//
//    public DeliveryRepo getDeliveryRepo() {
//        return deliveryRepo;
//    }
//
//   }
