package com.projetReseau.model;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Contains various state enums for the application")

public abstract class States {
    /* Gear boxes types */
    @Schema(description = "Types of Gear Boxes")
    public enum GearBox {
        @Schema(description = "Manual Gear Box", example = "0")
        MANUAL((short) 0),

        @Schema(description = "Automatic Gear Box", example = "1")
        AUTOMATIC((short) 1),

        @Schema(description = "All Gear Box Types", example = "2")
        ALL((short) 2);

        private final short value;
        GearBox(short value) { this.value = value; }
        public short value() { return this.value; }
    }

    /* Vehicle states */
    public enum VehicleState {
        @Schema(description = "Vehicle is in use", example = "0")
        ON_USE((short) 0),

        @Schema(description = "Vehicle is on revision", example = "1")
        ON_REVISION((short) 1),

        @Schema(description = "Vehicle is ready", example = "2")
        READY((short) 2);

        private final short value;
        VehicleState(short value) { this.value = value; };
        public short value() { return this.value; };
    }

    /* Driver State  */
    public enum DriverState {
        @Schema(description = "Driver is ready", example = "0")
        READY((short) 0),

        @Schema(description = "Driver is on rest", example = "1")
        ON_REST((short) 1),

        @Schema(description = "Driver is traveling", example = "2")
        TRAVELLING((short) 2);

        private final short value;
        DriverState(short value) { this.value = value; };
        public short value() { return this.value; };
    }

    /* Travel State */
    public enum TravelState {
        @Schema(description = "Travel is published", example = "0")
        PUBLISHED((short) 0),

        @Schema(description = "Travel is not published", example = "1")
        NOT_PUBLISHED((short) 1),

        @Schema(description = "Travel is ongoing", example = "2")
        ONGOING((short) 2),

        @Schema(description = "Travel is cancelled", example = "3")
        CANCELLED((short) 3),

        @Schema(description = "Travel is finished", example = "4")
        FINISHED((short) 4);

        private final short value;
        TravelState(short value) { this.value = value; }
        public short value() { return this.value; }
    }

    /* Planning state */
    public enum PlanningState {
        @Schema(description = "Planning is published", example = "0")
        PUBLISHED((short) 0),

        @Schema(description = "Planning is not published", example = "1")
        NOT_PUBLISHED((short) 1);

        private final  short value;
        PlanningState(short value) { this.value = value; }
        public short value() { return this.value; }
    }
}
