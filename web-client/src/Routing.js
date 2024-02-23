import { Routes, Route, Outlet, Link } from "react-router-dom";

export default function Routing() {
  return (
    <Routes>
        <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="/ping" element={<PingPage />} />
            {/* <Route path="*" element={<NoMatch />} /> */}
        </Route>
    </Routes>
  )
}

function Layout() {
  return (
    <div>
      I am layout babu
    </div>
  )
}

function Home() {
  return (
    <div>
      I am home babu
    </div>
  )
}

function PingPage() {
  return (
    <div>
      I am ping babu
    </div>
  )
}