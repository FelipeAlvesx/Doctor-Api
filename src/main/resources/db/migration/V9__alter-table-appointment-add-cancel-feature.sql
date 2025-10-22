ALTER TABLE appointment
            ADD COLUMN canceled BOOLEAN DEFAULT FALSE,
            ADD COLUMN cancellation_reason VARCHAR(255);