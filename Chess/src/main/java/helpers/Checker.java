package helpers;


import lombok.RequiredArgsConstructor;
import models.ValidationProps;
import validators.IMoveValidator;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class Checker {
    private final ValidationProps props;
    public Boolean or(List<Supplier<IMoveValidator>> conditions){
        return conditions.stream().map(supplier -> supplier.get().validate(props))
                .reduce((result1, result2)->result1 || result2)
                .orElse(true);
    }
    public Boolean and(List<Supplier<IMoveValidator>> conditions){
        return conditions.stream().map(supplier -> supplier.get().validate(props))
                .reduce((result1, result2)->result1 && result2)
                .orElse(true);
    }
    public Boolean is(Supplier<IMoveValidator> condition){
        return condition.get().validate(props);
    }
}
