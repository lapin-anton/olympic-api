import * as React from "react";
import './App.css';

import Home from './pages/home-page/home-page';
import GameList from "./components/game-list/game-list";
import SportList from "./components/sport-list/sport-list";

import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import AppBar from "@mui/material/AppBar";
import {Link, Route, Switch} from 'react-router-dom';
import {Box} from "@mui/material";

export default function App() {

    return (
        <div>
            <AppBar position="static">
                <Toolbar>
                    <Typography>
                        <Box fontWeight={"fontWeightBold"}>
                            Olympic UI
                        </Box>
                    </Typography>
                    <Link to={"/"}>
                        <Typography variant="body2">
                            Home
                        </Typography>
                    </Link>
                    <Link to={"/games"}>
                        <Typography variant="body2">
                            Games
                        </Typography>
                    </Link>
                    <Link to={"/sports"}>
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
            </Switch>
        </div>
    );

}
