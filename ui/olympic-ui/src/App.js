import * as React from "react";
import './App.css';

import Home from './pages/home-page/home-page';
import GameList from "./components/game-list/game-list";
import SportList from "./components/sport-list/sport-list";

import GamePage from './pages/games-page/game-page';

import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import AppBar from "@mui/material/AppBar";
import {Link, Route, Switch} from 'react-router-dom';
import {Box} from "@mui/material";
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
                {/*<Route exact path="/athletes" component={GameList}/>*/}

                <Route exact path="/game/:id" component={GamePage}/>
            </Switch>
        </div>
    );

}

export default withStyles(styles)(App);
