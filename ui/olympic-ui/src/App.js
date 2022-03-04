import * as React from "react";
import './App.css';

import Home from './pages/home-page/home-page';
import GameList from "./components/game-list/game-list";
import SportList from "./components/sport-list/sport-list";

import GamePage from './pages/games-page/game-page';
import AthletePage from "./pages/athletes-page/athlete-page";

import {
    Toolbar,
    Typography,
    AppBar,
    Box
} from "@mui/material";

import {
    Link,
    Route,
    Switch
} from 'react-router-dom';

import { withStyles } from '@mui/styles';
import {styles} from "./css-common";

function App(props) {

    const {classes} = props;

    return (
        <div>
            <AppBar position="fixed">
                <Toolbar>
                    <Typography>
                        <Box fontWeight={"fontWeightBold"}>
                            Olympic UI
                        </Box>
                    </Typography>
                    <Link to={"/"} className={classes.link}>
                        <Typography variant="body2">
                            Home
                        </Typography>
                    </Link>
                    <Link to={"/games"} className={classes.link}>
                        <Typography variant="body2">
                            Games
                        </Typography>
                    </Link>
                    <Link to={"/sports"} className={classes.link}>
                        <Typography variant="body2">
                            Sports
                        </Typography>
                    </Link>
                    {/*<Link to={"/athletes"}>*/}
                    {/*    <Typography variant="body2">*/}
                    {/*        Athletes*/}
                    {/*    </Typography>*/}
                    {/*</Link>*/}
                </Toolbar>
            </AppBar>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route exact path="/games" component={GameList}/>
                <Route exact path="/sports" component={SportList}/>

                <Route exact path="/game/:id" component={GamePage}/>
                <Route exact path="/athlete/:id" component={AthletePage}/>
            </Switch>
        </div>
    );

}

export default withStyles(styles)(App);
