import {Grid} from "@mui/material";
import Box from "@mui/material/Box";
import GameCard from "../game-card/game-card";
import {Component} from "react";
import GameService from "../../services/game-service";

class GameList extends Component {

    constructor(props) {
        super(props);
        this.fetchGames = this.fetchGames.bind(this);
        this.state = {
            games: []
        }
    }

    componentDidMount() {
        this.fetchGames();
    }

    async fetchGames() {
        await GameService.getAllGames()
            .then(response =>
                this.setState({
                    games: response.data
                }))
            .catch(e =>
                console.log(e)
            )
    }

    render () {

        const {games} = this.state;

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
    };
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

export default GameList;