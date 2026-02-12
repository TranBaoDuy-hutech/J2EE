package com.travel3d.vietlutravel.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Tours")
public class Tours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TourID")
    private Integer tourID;

    @Column(name = "TourName")
    private String tourName;

    @Column(name = "Location")
    private String location;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "DurationDays")
    private Integer durationDays;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "ImageUrl")
    private String imageUrl;

    @Column(name = "DepartureLocation")
    private String departureLocation;

    @Column(name = "HotelName")
    private String hotelName;

    @Column(name = "IsHot")
    private Boolean isHot;

    @Column(name = "Itinerary")
    private String itinerary;

    @Column(name = "Transportation")
    private String transportation;

    // ===== Getter & Setter =====

    public Integer getTourID() { return tourID; }
    public void setTourID(Integer tourID) { this.tourID = tourID; }

    public String getTourName() { return tourName; }
    public void setTourName(String tourName) { this.tourName = tourName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getDurationDays() { return durationDays; }
    public void setDurationDays(Integer durationDays) { this.durationDays = durationDays; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDepartureLocation() { return departureLocation; }
    public void setDepartureLocation(String departureLocation) { this.departureLocation = departureLocation; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public Boolean getIsHot() { return isHot; }
    public void setIsHot(Boolean isHot) { this.isHot = isHot; }

    public String getItinerary() { return itinerary; }
    public void setItinerary(String itinerary) { this.itinerary = itinerary; }

    public String getTransportation() { return transportation; }
    public void setTransportation(String transportation) { this.transportation = transportation; }
}
