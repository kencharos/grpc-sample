package my.grpcserver;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.ReflectionUtils;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Descriptors.FieldDescriptor;

import validator.Validator.Range;

public class SampleValidator {

    public Optional<List<ValidationResult>> validate(Object obj) {
        if (!(obj instanceof AbstractMessage)) {
            return Optional.empty();
        }
        var message = (AbstractMessage)obj;

        var fields = message.getAllFields();
        var results = fields.entrySet().stream().flatMap(e -> validateOne(e.getKey(), e.getValue()).stream())
              .collect(Collectors.toList());

        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }


    private List<ValidationResult> validateOne(FieldDescriptor desc, Object value) {

        var ops = desc.getOptions().getAllFields();

        return ops.values().stream().filter(v -> v instanceof Range)
           .map(v -> validateRange((Range)v, desc.getName(), value))
           .filter(Optional::isPresent)
           .map(Optional::get)
           .collect(Collectors.toList());
    }

    private Optional<ValidationResult> validateRange(Range range, String name, Object value) {

        var i = (Integer) value;
        if (range.getMin() <= i && i <= range.getMax()) {
            return Optional.empty();
        }
        var res =  new ValidationResult(name, i + " is not between " + range.getMin() + " from " + range.getMax());
        return Optional.of(res);
    }

    public static class ValidationResult {
        public final String name;
        public final String message;

        public ValidationResult(String name, String message) {
            this.name = name;
            this.message = message;
        }

        @Override
        public String toString() {
            return name +":"+message;
        }
    }

}
