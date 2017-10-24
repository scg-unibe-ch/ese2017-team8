package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the delivery controlling.
 * It handles the creating of the input blank in the @link{parcelForm} method.
 * Then it controls the submit function with the @link{parcelSubmit} method.
 * Additional it also handles the search for all parcels and drivers,
 * with the @link{showAllParcel} and @link{showAllDrivers} methods.
 *
 * @author Team8
 * @version 1.0
 */

//@Controller
//public class ParcelController {
//
//    @Autowired
//    public ParcelRepo parcelRepo;
//
//
//
//}