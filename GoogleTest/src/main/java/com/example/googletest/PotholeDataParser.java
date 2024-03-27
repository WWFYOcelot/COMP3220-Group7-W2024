/**
 * @author Faraan Rashid
 * @version 1.0
 *
 * The PotholeServiceRequest class provides structures for parsing pothole service requests from JSON files acquired from
 *  Windsor's Open Data service,whose data values are then accessible through its methods.
 *
 * Note that while this class should use proper encapsulation, ie. private fields and getters and setters, the JSON parser we used wasn't configured to handle this.
 *  As we considered reconfiguring the JSON parser to be outside the scope of this iteration, we opted to postpone this change to a future iteration.
 *
 */

package com.example.googletest;

import com.google.gson.annotations.SerializedName;

public class PotholeDataParser {
    /**
     * Description of the service request.
     */
    @SerializedName("Service Request Description")
    public String serviceRequestDescription;

    /**
     * Department responsible for handling the service request.
     */
    @SerializedName("Department")
    public String department;

    /**
     * Method through which the service request was received.
     */
    @SerializedName("Method Received")
    public String methodReceived;

    /**
     * Date when the service request was created.
     */
    @SerializedName("Created Date")
    public String createdDate;

    /**
     * Address or block where the pothole is located.
     */
    @SerializedName("Block/Address")
    public String blockAddress;

    /**
     * Street where the pothole is located.
     */
    @SerializedName("Street")
    public String street;

    /**
     * Ward associated with the pothole location.
     */
    @SerializedName("Ward")
    public String ward;

    /**
     * Sub-type of the pothole service request.
     */
    @SerializedName("Sub-Type")
    public String subType;

    /**
     * Returns a string representation of the pothole service request.
     *
     * @return A string containing the details of the service request.
     */
    @Override
    public String toString() {
        return "PotholeServiceRequest{" +
                "serviceRequestDescription='" + serviceRequestDescription + '\'' +
                ", department='" + department + '\'' +
                ", methodReceived='" + methodReceived + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", blockAddress='" + blockAddress + '\'' +
                ", street='" + street + '\'' +
                ", ward='" + ward + '\'' +
                ", subType='" + subType + '\'' +
                '}';
    }
}
