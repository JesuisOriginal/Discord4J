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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package discord4j.core.object.entity;

import discord4j.core.Client;
import discord4j.core.object.data.ChannelData;

import java.util.Optional;

/** A Discord voice channel. */
public final class VoiceChannel extends BaseGuildChannel {

	/**
	 * Constructs an {@code VoiceChannel} with an associated client and Discord data.
	 *
	 * @param client The Client associated to this object, must be non-null.
	 * @param channel The raw data as represented by Discord, must be non-null.
	 */
	public VoiceChannel(final Client client, final ChannelData channel) {
		super(client, channel);
	}

	/**
	 * Gets the bitrate (in bits) for this voice channel.
	 *
	 * @return Gets the bitrate (in bits) for this voice channel.
	 */
	public int getBitrate() {
		return Optional.ofNullable(data.getBitrate()).orElseThrow(IllegalStateException::new);
	}

	/**
	 * Gets the user limit of this voice channel.
	 *
	 * @return The user limit of this voice channel.
	 */
	public int getUserLimit() {
		return Optional.ofNullable(data.getUserLimit()).orElseThrow(IllegalStateException::new);
	}
}
