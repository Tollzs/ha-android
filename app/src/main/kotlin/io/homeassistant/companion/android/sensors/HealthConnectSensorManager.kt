        return AggregateRequest(
            metrics = setOf(metric),
            timeRangeFilter = TimeRangeFilter.between(
                LocalDateTime.of(today, LocalTime.MIDNIGHT),
                now,
            ),
        )
    }

    private fun buildNutritionRecordsRequest(): ReadRecordsRequest<NutritionRecord> {
        val now = LocalDateTime.now()
        val today = now.toLocalDate()
        return ReadRecordsRequest(
            recordType = NutritionRecord::class,
            timeRangeFilter = TimeRangeFilter.between(
                LocalDateTime.of(today, LocalTime.MIDNIGHT),
                now,
            ),
            ascendingOrder = true,
            pageSize = 100,
        )
    }

    private fun <T : Record> buildReadRecordsRequest(request: KClass<T>): ReadRecordsRequest<T> {
        return ReadRecordsRequest(
            recordType = request,
            timeRangeFilter = TimeRangeFilter.between(
                Instant.now().minus(30, ChronoUnit.DAYS),
                Instant.now(),
            ),
            ascendingOrder = false,
            pageSize = 1,
        )
    }

    private fun buildAggregationAttributes(result: AggregationResult): Map<String, Any> {
        return mapOf(
            "endTime" to Instant.now(),
            "sources" to result.dataOrigins.map { it.packageName },
        )
    }

    private fun getRelationToMeal(relation: Int): String {
        return when (relation) {
            BloodGlucoseRecord.RELATION_TO_MEAL_FASTING -> "fasting"
            BloodGlucoseRecord.RELATION_TO_MEAL_BEFORE_MEAL -> "before_meal"
            BloodGlucoseRecord.RELATION_TO_MEAL_GENERAL -> "general"
            BloodGlucoseRecord.RELATION_TO_MEAL_AFTER_MEAL -> "after_meal"
            BloodGlucoseRecord.RELATION_TO_MEAL_UNKNOWN -> STATE_UNKNOWN
            else -> STATE_UNKNOWN
        }
    }

    private fun getSpecimenSource(source: Int): String {
        return when (source) {
            BloodGlucoseRecord.SPECIMEN_SOURCE_CAPILLARY_BLOOD -> "capillary_blood"
            BloodGlucoseRecord.SPECIMEN_SOURCE_INTERSTITIAL_FLUID -> "interstitial_fluid"
            BloodGlucoseRecord.SPECIMEN_SOURCE_PLASMA -> "plasma"
            BloodGlucoseRecord.SPECIMEN_SOURCE_SERUM -> "serum"
            BloodGlucoseRecord.SPECIMEN_SOURCE_TEARS -> "tears"
            BloodGlucoseRecord.SPECIMEN_SOURCE_UNKNOWN -> STATE_UNKNOWN
            BloodGlucoseRecord.SPECIMEN_SOURCE_WHOLE_BLOOD -> "whole_blood"
            else -> STATE_UNKNOWN
        }
    }

    private fun getMealType(mealType: Int): String {
        return when (mealType) {
            MealType.MEAL_TYPE_BREAKFAST -> "breakfast"
            MealType.MEAL_TYPE_LUNCH -> "lunch"
            MealType.MEAL_TYPE_DINNER -> "dinner"
            MealType.MEAL_TYPE_SNACK -> "snack"
            MealType.MEAL_TYPE_UNKNOWN -> STATE_UNKNOWN
            else -> STATE_UNKNOWN
        }
    }

    private fun getBloodPressureBodyPosition(position: Int): String {
        return when (position) {
            BloodPressureRecord.BODY_POSITION_LYING_DOWN -> "lying_down"
            BloodPressureRecord.BODY_POSITION_RECLINING -> "reclining"
            BloodPressureRecord.BODY_POSITION_SITTING_DOWN -> "sitting_down"
            BloodPressureRecord.BODY_POSITION_STANDING_UP -> "standing_up"
            BloodPressureRecord.BODY_POSITION_UNKNOWN -> STATE_UNKNOWN
            else -> STATE_UNKNOWN
        }
    }

    private fun getBloodPressureMeasurementLocation(location: Int): String {
        return when (location) {
            BloodPressureRecord.MEASUREMENT_LOCATION_LEFT_WRIST -> "left_wrist"
            BloodPressureRecord.MEASUREMENT_LOCATION_LEFT_UPPER_ARM -> "left_upper_arm"
            BloodPressureRecord.MEASUREMENT_LOCATION_RIGHT_WRIST -> "right_wrist"
            BloodPressureRecord.MEASUREMENT_LOCATION_RIGHT_UPPER_ARM -> "right_upper_arm"
            BloodPressureRecord.MEASUREMENT_LOCATION_UNKNOWN -> STATE_UNKNOWN
            else -> STATE_UNKNOWN
        }
    }

    private fun getMeasurementMethod(method: Int): String {
        return when (method) {
            Vo2MaxRecord.MEASUREMENT_METHOD_COOPER_TEST -> "cooper_test"
            Vo2MaxRecord.MEASUREMENT_METHOD_HEART_RATE_RATIO -> "heart_rate_ratio"
            Vo2MaxRecord.MEASUREMENT_METHOD_METABOLIC_CART -> "metabolic_cart"
            Vo2MaxRecord.MEASUREMENT_METHOD_MULTISTAGE_FITNESS_TEST -> "multistage_fitness_test"
            Vo2MaxRecord.MEASUREMENT_METHOD_OTHER -> "other"
            Vo2MaxRecord.MEASUREMENT_METHOD_ROCKPORT_FITNESS_TEST -> "rockport_fitness_test"
            else -> STATE_UNKNOWN
        }
    }

    private fun getBodyTemperatureMeasurementLocation(location: Int): String {
        return when (location) {
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_ARMPIT -> "armpit"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_EAR -> "ear"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_FINGER -> "finger"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_FOREHEAD -> "forehead"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_MOUTH -> "mouth"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_RECTUM -> "rectum"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_TEMPORAL_ARTERY -> "temporal_artery"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_TOE -> "toe"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_UNKNOWN -> STATE_UNKNOWN
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_VAGINA -> "vagina"
            BodyTemperatureMeasurementLocation.MEASUREMENT_LOCATION_WRIST -> "wrist"
            else -> STATE_UNKNOWN
        }
    }
}
