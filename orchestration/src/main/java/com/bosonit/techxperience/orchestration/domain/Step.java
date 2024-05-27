package com.bosonit.techxperience.orchestration.domain;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


@Getter
@Setter
public class Step {
    private Consumer operation;
    private Consumer compensation;

}