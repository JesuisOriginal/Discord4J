/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J. If not, see <http://www.gnu.org/licenses/>.
 */

package discord4j.rest.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the strategies to request and response body conversion.
 */
public interface ExchangeStrategies {

    /**
     * Retrieve the list of writer strategies to convert a request body.
     *
     * @return a list of writer strategies
     */
    List<WriterStrategy<?>> writers();

    /**
     * Retrieve the list of reader strategies to convert a response body.
     *
     * @return a list of reader strategies
     */
    List<ReaderStrategy<?>> readers();

    /**
     * Return an {@link discord4j.rest.http.ExchangeStrategies} using the defaults for processing JSON requests using
     * Jackson.
     *
     * @param mapper the Jackson object mapper
     * @return the built strategies
     */
    static ExchangeStrategies withJacksonDefaults(ObjectMapper mapper) {
        List<WriterStrategy<?>> writerStrategies = new ArrayList<>();
        writerStrategies.add(new MultipartWriterStrategy(mapper));
        writerStrategies.add(new JacksonWriterStrategy(mapper));
        writerStrategies.add(new EmptyWriterStrategy());
        List<ReaderStrategy<?>> readerStrategies = new ArrayList<>();
        readerStrategies.add(new JacksonReaderStrategy(mapper));
        readerStrategies.add(new EmptyReaderStrategy());
        return new DefaultExchangeStrategies(writerStrategies, readerStrategies);
    }

    /**
     * Return a mutable, empty builder for creating an {@link discord4j.rest.http.ExchangeStrategies}
     *
     * @return the builder
     */
    static Builder empty() {
        return new Builder() {

            private final List<WriterStrategy<?>> writerStrategies = new ArrayList<>();
            private final List<ReaderStrategy<?>> readerStrategies = new ArrayList<>();

            @Override
            public Builder writerStrategy(WriterStrategy<?> writerStrategy) {
                writerStrategies.add(writerStrategy);
                return this;
            }

            @Override
            public Builder readerStrategy(ReaderStrategy<?> readerStrategy) {
                readerStrategies.add(readerStrategy);
                return this;
            }

            @Override
            public ExchangeStrategies build() {
                return new DefaultExchangeStrategies(writerStrategies, readerStrategies);
            }
        };
    }

    /**
     * A mutable builder for creating an {@link discord4j.rest.http.ExchangeStrategies}
     */
    interface Builder {

        /**
         * Add a new writer strategy for request body conversion.
         *
         * @param writerStrategy the strategy to add
         * @return this builder, for chaining
         */
        Builder writerStrategy(WriterStrategy<?> writerStrategy);

        /**
         * Add a new reader strategy for response body conversion.
         *
         * @param readerStrategy the strategy to add
         * @return this builder, for chaining
         */
        Builder readerStrategy(ReaderStrategy<?> readerStrategy);

        /**
         * Builds the {@link discord4j.rest.http.ExchangeStrategies}
         *
         * @return the built strategies
         */
        ExchangeStrategies build();
    }
}
