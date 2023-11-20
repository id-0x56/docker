-- INSERT INTO users (email, password) VALUES
--     ('howard@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
--     ('kathleen@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
--     ('marie@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
--     ('louise@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
--     ('sharon@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
--     ('danny@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
--     ('roxie@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO')
-- ON CONFLICT (email) DO NOTHING;

INSERT IGNORE users (email, password) VALUES
    ('howard@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('kathleen@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('marie@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('louise@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('sharon@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('danny@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO'),
    ('roxie@example.com', '$2a$10$aBH0YBgQgrd6R9jmNTF2pONDjkyP3wrfuXTRLUs6DjUeeolpQ9/dO');
