package services;

import lombok.NonNull;
import models.Hotel;

import java.util.Date;
import java.util.List;

public interface ISearch {
    public List<Hotel> searchHotels(@NonNull final Date checkInDate, @NonNull final Date checkOutDate);
    public List<Hotel> searchHotels(@NonNull final Date checkInDate, @NonNull final Date checkOutDate, @NonNull final String city);
    public List<Hotel> searchHotels(@NonNull final Date checkInDate, @NonNull final Date checkOutDate,
                                    @NonNull final Double lowPrice, @NonNull final Double highPrice);
    public List<Hotel> searchHotels(@NonNull final Date checkInDate, @NonNull final Date checkOutDate, @NonNull final String city,
                                    @NonNull final Double lowPrice, @NonNull final Double highPrice);

}
