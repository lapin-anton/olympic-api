import {Grid} from "@mui/material";
import Box from "@mui/material/Box";
import GameCard from "../game-card/game-card";

export default function GameList(props) {

    const {games} = props;

    return (
        <Box sx={{ flexGrow: 1 }} padding={6}>
            <Grid container spacing={1}>
                {games.map((game, i) => {
                        if (i % 4 === 0) {
                            return (
                                <Grid container item spacing={3} marginBottom={3}>
                                    <Row games={games.slice(i, i + 4)}/>
                                </Grid>
                            )
                        }
                    }
                )}
            </Grid>
        </Box>
    );
}

function Row(props) {

    const {games} = props;

    return (
        <>
            {games.map(game =>
                <Grid item xs={3}>
                    <GameCard
                        img={game.thumbnailUrl}
                        city={game.city}
                        year={game.year}
                        season={game.type}
                    />
                </Grid>
            )}

        </>
    );
}